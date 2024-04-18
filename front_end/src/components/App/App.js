import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import bookShopService from "../../repository/bookShopRepository";
import Header from "../../components/Header/header";
import Books from "../Books/BooksList/books";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";
import CategoriesList from '../Categories/CategoriesList/categoriesList';

class App extends Component{
  constructor(props) {
    super(props);
    this.state = {
      books: [],
      categories: [],
      authors: [],
      selectedBook: {}
    };
  }

  render(){
    return(
      <Router>
        
        <Header/>
        <main>
          <div className="container">
            <Routes>
            <Route path={"/"} element = {
              <Books books={this.state.books}
                    onDelete={this.deleteBook}
                    onEdit={this.getBook}
                    onMarkTaken={this.markTaken}
                    />}/>
            <Route path={"/books"} element = {
              <Books books={this.state.books}
                    onDelete={this.deleteBook}
                    onEdit={this.getBook}
                    onMarkTaken={this.lendBook}
                    />}/>

            <Route path={"/books/create"} element = {
              <BookAdd categories={this.state.categories}
                    authors={this.state.authors}
                    onAddBook={this.addBook}
                    />}/>

            <Route path={"/books/edit/:id"} element = {
              <BookEdit categories={this.state.categories}
                          authors={this.state.authors}
                          onEditBook={this.editBook}
                          book={this.state.selectedBook}/>}/>
            <Route path={"/categories"} element = {
              <CategoriesList categories={this.state.categories}/>}/>
            </Routes>
          </div>
        </main>
          
      </Router>
    )
  }

  componentDidMount(){
    this.fetchData();
  }

  fetchData = () =>{
    this.loadBooks();
    this.loadCategories();
    this.loadAuthors();
  }

  addBook = (name, category, author, availableCopies) => {
    bookShopService.addBook(name, category, author, availableCopies)
        .then(() => {
            this.loadBooks();
        });
  }

  deleteBook = (id) => {
    bookShopService.deleteBook(id)
        .then(() => {
            this.loadBooks();
        });
  }

  lendBook = (id) => {
    bookShopService.lendBook(id)
        .then(() => {
            this.loadBooks();
        });
  }

  getBook = (id) => {
    bookShopService.getBook(id)
        .then((data) => {
            this.setState({
                selectedBook: data.data
            })
        })
  }

  editBook = (id, name, category, authorId, availableCopies) => {
    bookShopService.editBook(id, name, category, authorId, availableCopies)
        .then(() => {
            this.loadBooks();
        });
  }

  loadBooks = () => {
    bookShopService.fetchBooks()
        .then((data) => {
            this.setState({
                books: data.data
            })
        });
  }
  
  loadCategories = () => {
    bookShopService.fetchCategories()
        .then((data) => {
            this.setState({
                categories: data.data
            })
        });
  }
  
  loadAuthors = () => {
    bookShopService.fetchAuthors()
        .then((data) => {
            this.setState({
                authors: data.data
            })
        });
  }
}

export default App;
