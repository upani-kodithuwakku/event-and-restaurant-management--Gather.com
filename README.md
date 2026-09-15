# Gather — shared foundation

This branch contains the database schema, shared Spring Boot infrastructure, and React setup. Feature modules will be added by the team. The frontend currently displays a foundation page; authentication and feature endpoints are not implemented here.

## Run locally

Requirements: Java 21+, Maven, Node.js/npm, and MySQL.

1. Run `database/01_create_database.sql` in MySQL Workbench. It creates the schema without sample data. The script targets `restaurant_event_db`.
2. Copy `restaurant-event-backend/.env.example` to `.env` in that same backend folder. Set your database credentials and a random JWT secret.
3. In `restaurant-event-backend`, run `mvn spring-boot:run`. The health endpoint is `http://localhost:8080/api/health`.
4. In `restaurant-event-frontend`, run `npm ci`, then `npm run dev`. Vite proxies `/api` to the backend; see `vite.config.ts` for the port.
5. Build with `mvn package` in the backend and `npm run build` in the frontend.

Required roles are initialized automatically. Demo accounts are disabled by default. A new database will need an admin account provisioned when the auth module is integrated. No existing `.env` files or credentials have been copied.

## Shared ownership

- Backend: `common/`, `security/`, `config/`, startup class, Maven configuration, and user/role entities and repositories required by security.
- Frontend: build configuration, styles, types, layouts, UI components, `AppContext.tsx`, and `services/api.ts`.
- `App.tsx` is a minimal entry point. Integrate routes as feature pages are added.
- `AppContext.tsx` and `api.ts` retain the existing shared contracts. Calls to feature endpoints require their corresponding backend modules.
- Coordinate changes to shared files. Do not overwrite the entire API file when adding a member module.

## Team branches

| Member | Branch | Modules |
|---|---|---|
| Samarasinghe | feature/01-samarasinghe-customer-management | auth, users, notifications, reports |
| Ahamed | feature/02-ahamed-event-booking | events, billing |
| Batagodage | feature/03-batagodage-menu-orders | menu, orders |
| Kodithuwakku | feature/04-kodithuwakku-table-reservations | reservations |
| Labijan | feature/05-labijan-inventory-supply | inventory |
| Gunasekara | feature/06-gunasekara-staff-scheduling | staff |

Merge `develop` into your member branch to receive the shared foundation. Integrate completed member work into `develop` before promoting to `main`.

Use the original member packages for feature files; this repository does not duplicate their archives or full project snapshot. The current shared source is used here, including the setting that disables demo seeding.
