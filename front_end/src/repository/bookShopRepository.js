import axios from "../custom-axios/axios";

const bookShopService = {
    fetchBooks: () => {return axios.get("/books")},
    fetchCategories: () => {return axios.get("/books/categories")},
    fetchAuthors: () => {return axios.get("/books/authors")},
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name, category, authorId, availableCopies) => {
        return axios.post("/books/create", {
            "name": name,
            "category": category,
            "authorId": authorId,
            "availableCopies": availableCopies,
        });
    },
    lendBook: (id) => {
        return axios.put(`/books/lendBook/${id}`);
    },
    editBook: (id, name, category, authorId, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "authorId": authorId.id,
            "availableCopies": availableCopies,
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
}

export default bookShopService;