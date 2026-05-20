No ViewModel is used for this project
# 1. State Management + Compose Basics

### Prompt 1 — Favorite Products

Enhance the product list app.

Requirements:

* Add a ⭐ icon beside each product.
* Tapping it toggles favorite state.
* Favorites appear at the top of the list.
* Preserve sorting.

Concepts practiced:

* `mutableStateOf`
* list updates
* recomposition
* immutable UI updates

---

### Prompt 2 — Add Product

Add an input field and button.

Requirements:

* User can type product name.
* Clicking **Add Product** inserts into list.
* Empty input should not add.
* Clear text after insert.

Practice:

* `remember`
* state hoisting
* input validation

---

### Prompt 3 — Delete Product

Requirements:

* Swipe-to-delete OR button delete.
* Show confirmation dialog before delete.

Practice:

* dialogs
* lazy list updates
* UI state

---

### Prompt 4 — Product Counter

Requirements:

* Display total products count.
* Display filtered count when search active.

Example:

```text
Showing 2 of 8 products
```

Practice:

* derived UI state
* recomposition

---

# 2. Search + Filtering (build on your app)

### Prompt 5 — Live Search Without Button

Requirements:

* Keep debounce.
* Add loading indicator while debounce waits.
* Show:

```text
Searching...
```

Practice:

* `snapshotFlow`
* `debounce`
* async UI state
# ComposeCraft
