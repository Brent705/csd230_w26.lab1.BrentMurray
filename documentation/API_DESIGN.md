## **Books** (`/api/rest/books`)

### `GET` `/api/rest/books`

- **Expects:** Nothing.
- **Returns:** `200 OK` with an array of `BookEntity` JSON objects.

### `POST` `/api/rest/books`
- **Expects:** JSON `BookEntity` in the request body.
- **Returns:** `201 Created` with the saved `BookEntity`.

### `GET` `/api/rest/books/{id}`
- **Expects:** `id` (Long) as a path variable.
- **Returns:** `200 OK` with a single `BookEntity`, or `404 Not Found` if it doesn't exist.

### **`PUT` `/api/rest/books/{id}`**
- **Expects:** `id` (Long) as a path variable and JSON `BookEntity` in the request body.
- **Returns:** `200 OK` (if updated) or `201 Created` (if a new one was made) with the saved `BookEntity`.

### **`DELETE` `/api/rest/books/{id}`**
- **Expects:** `id` (Long) as a path variable.
- **Returns:** `204 No Content` (Empty response).

## **Users** (`/api/rest/users`)

### `GET` `/api/rest/users`
- **Expects:** Nothing.
- **Returns:** `200 OK` with an array of `UserEntity` JSON objects.

### **`POST` `/api/rest/users`**
- **Expects:** JSON `UserEntity` in the request body (`username`, `password`, `role`). 
- **Returns:** `201 Created` with the saved `UserEntity`.

### **`GET` `/api/rest/users/{id}`**
- **Expects:** `id` (Long) as a path variable.
- **Returns:** `200 OK` with a single `UserEntity`, or `404 Not Found`.

### **`PUT` `/api/rest/users/{id}`**
- **Expects:** `id` (Long) as a path variable and JSON `UserEntity` in the request body.
- **Returns:** `200 OK` or `201 Created` with the saved `UserEntity`.

### **`DELETE` `/api/rest/users/{id}`**
- **Expects:** `id` (Long) as a path variable.
- **Returns:** `204 No Content`.

## **Tickets** (`/api/rest/tickets`)

### **`GET` `/api/rest/tickets`**
- **Expects:** Nothing.
- **Returns:** `200 OK` with an array of `TicketEntity` JSON objects.

### **`POST` `/api/rest/tickets`**
- **Expects:** JSON `TicketEntity` in the request body (`description`, `price`).
- **Returns:** `201 Created` with the saved `TicketEntity`.

### **`GET` `/api/rest/tickets/{id}`**
- **Expects:** `id` (Long) as a path variable.
- **Returns:** `200 OK` with `TicketEntity` or `404 Not Found`.

### **`PUT` `/api/rest/tickets/{id}`**
- **Expects:** `id` (Long) as a path variable and JSON `TicketEntity` in request body.
- **Returns:** `200 OK` or `201 Created` with the saved `TicketEntity`.

### **`DELETE` `/api/rest/tickets/{id}`**
- **Expects:** `id` (Long) as a path variable.
- **Returns:** `204 No Content`.

## **Magazines** (`/api/rest/magazines`)

### **`GET` `/api/rest/magazines`**
- **Expects:** Nothing.
- **Returns:** `200 OK` with an array of `MagazineEntity` JSON objects.

### **`POST` `/api/rest/magazines`**
- **Expects:** JSON `MagazineEntity` (`title`, `price`, `copies`, `orderQty`, `currentIssue`).
- **Returns:** `201 Created` with the saved `MagazineEntity`.

### **`GET` `/api/rest/magazines/{id}`**
- **Expects:** `id` as a path variable.
- **Returns:** `200 OK` with `MagazineEntity` or `404 Not Found`.

### **`PUT` `/api/rest/magazines/{id}`**
- **Expects:** `id` path variable and JSON `MagazineEntity`.
- **Returns:** `200 OK` or `201 Created` with the saved `MagazineEntity`.

### **`DELETE` `/api/rest/magazines/{id}`**
- **Expects:** `id` as a path variable.
- **Returns:** `204 No Content`.

## **Disc Magazines** (`/api/rest/discmags`)

### **`GET` `/api/rest/discmags`**
- **Expects:** Nothing.
- **Returns:** `200 OK` with an array of Disc Magazine objects.

### **`POST` `/api/rest/discmags`**
- **Expects:** JSON `DiscMagEntity` in the request body. 
- **Returns:** `201 Created` with the newly saved object (including its generated `id`).

### **`GET` `/api/rest/discmags/{id}`**
- **Expects:** `id` (Long) as a path variable in the URL (e.g., `/api/rest/discmags/1`).
- **Returns:** `200 OK` with a single Disc Magazine JSON object.

### **`PUT` `/api/rest/discmags/{id}`**
- **Expects:** `id` (Long) as a path variable, and a full JSON `DiscMagEntity` in the request body.
- **Returns:** `200 OK` (if it updated an existing record) or `201 Created` (if the `id` didn't exist, and it created a new one) with the saved JSON object.

### **`DELETE` `/api/rest/discmags/{id}`**
- **Expects:** `id` (Long) as a path variable.
- **Returns:** `204 No Content` (The request was successful, and there is no body returned).

## **Orders** (`/api/rest/orders`)

### **`GET` `/api/rest/orders`**
- **Expects:** Nothing.
- **Returns:** `200 OK` with an array of Order objects, including their nested products.

### **`POST` `/api/rest/orders`**
- **Expects:** JSON `OrderEntity` in the request body. To link existing products to the order, you usually just need to provide their IDs in the `products` array.
- **Returns:** `201 Created` with the newly saved Order object (including its generated `id`).

### **`GET` `/api/rest/orders/{id}`**
- **Expects:** `id` (Long) as a path variable (e.g., `/api/rest/orders/1`).
- **Returns:** `200 OK` with the Order JSON object, or a `404 Not Found` (Throws `OrderNotFoundException`) if the ID does not exist.

### **`PUT` `/api/rest/orders/{id}`**
- **Expects:** `id` (Long) as a path variable, and a JSON `OrderEntity` in the request body.
- **Returns:** `200 OK` (if updated) or `201 Created` (if a new order was created) with the saved Order object.

### **`DELETE` `/api/rest/orders/{id}`**
- **Expects:** `id` (Long) as a path variable.
- **Returns:** `204 No Content` (The order was successfully deleted).

## **Books UI** (`/books`)

### `GET` `/books`**
- **Expects:** Authenticated session.
- **Returns:** `bookList.html` (View containing a list of books).

### **`GET` `/books/{id}`**
- **Expects:** `id` path variable.
- **Returns:** `bookDetails.html` (View detailing a specific book).

### **`GET` `/books/add`** *(Requires ADMIN role)*
- **Expects:** Nothing.
- **Returns:** `addBook.html` (Form to create a book).

### **`POST` `/books/add`**
- **Expects:** Form-Url-Encoded `BookEntity` data.
- **Returns:** Redirects to `/books` (302).

### **`GET` `/books/edit/{id}`**
- **Expects:** `id` path variable.
- **Returns:** `editBook.html` (Form populated with existing book data).

### **`POST` `/books/edit/{id}`**
- **Expects:** `id` path var & Form-Url-Encoded `BookEntity` data.
- **Returns:** Redirects to `/books` (302).

### **`GET` `/books/delete/{id}`**
- **Expects:** `id` path variable.
- **Returns:** Redirects to `/books` (302).

## **Cart UI** (`/cart`)

### **`GET` `/cart`**
- **Expects:** Authenticated session (Principal user).
- **Returns:** `cartDetails.html` (View displaying current user's cart).

### **`GET` `/cart/add/{bookId}`**
- **Expects:** `bookId` path variable & Authenticated session.
- **Returns:** Redirects to `/books` (302) after adding the book to the cart.

### **`GET` `/cart/remove/{bookId}`**
- **Expects:** `bookId` path variable & Authenticated session.
- **Returns:** Redirects to `/cart` (302) after removing the book from the cart.

## **Auth** (`/login`, `/register`)

### **`GET` `/login`**
- **Expects:** Nothing.
- **Returns:** `login.html` (Spring Security login page).

### **`GET` `/register`**
- **Expects:** Nothing.
- **Returns:** `register.html` (User registration form).

### **`POST` `/register`**
- **Expects:** Form-Url-Encoded `UserEntity` data (`username`, `password`).
- **Returns:** Redirects to `/login` (302) upon successful registration.

## Utility / Documentation Endpoints

- **`GET` `/swagger-ui.html`** - Interactive UI to view and test your REST API.
- **`GET` `/v3/api-docs`** - The raw OpenAPI/Swagger JSON definition of your APIs.
- **`GET` `/h2-console`** - In-memory database administration tool.