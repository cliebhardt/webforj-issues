# spring-issue-test

Reproduces a webforJ 26.02 route security issue with Spring Security: **views are constructed before the route security check runs.** A view's constructor runs for users who are then denied, including anonymous users.

## The issue

`RouteRenderer.getOrCreateComponentWithLifecycle` creates the view through the conceiver first and only then fires `BEFORE_CREATE`. `RouteSecurityObserver` handles that event, but it only needs `component.getClass()`, and the class is already known before the view is created.

`AdminDashboardView` follows the dashboard pattern from the webforJ Spring routing docs: it builds its UI with an injected service in the constructor. Both layers require ADMIN:

- the route has `@RolesAllowed("ADMIN")`
- `MetricsService.getCurrentMetrics()` has `@PreAuthorize("hasRole('ADMIN')")`

Because the constructor runs before the route check, Spring method security rejects the service call first. The view fails to instantiate and the app shows a 500 error page instead of redirecting:

| Visitor | Expected | Actual |
|---|---|---|
| anonymous | redirect to `/signin` | 500 page, `AuthenticationCredentialsNotFoundException` |
| `user` / `password` (no ADMIN) | redirect to `/access-denied?reason=insufficient_roles` | 500 page, `AuthorizationDeniedException` |
| `admin` / `admin` | dashboard | dashboard |

## Reproduce

```bash
mvn
```

Open http://localhost:8080/ without signing in, or sign in as `user` / `password`.

`mvn verify` runs the Playwright tests. The two denial tests in `AdminDashboardViewIT` describe the expected behaviour and fail until the issue is fixed.
