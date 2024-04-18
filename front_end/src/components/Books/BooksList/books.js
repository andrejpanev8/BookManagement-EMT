import React, { Component } from "react";
import { Link } from "react-router-dom";
import ReactPaginate from "react-paginate";
import BookTerm from "../BookTerm/bookTerm";

class Books extends Component {
    constructor(props) {
        super(props);
        this.state = {
            page: 0,
            size: 5
        };
    }

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.books.length / this.state.size);
        const books = this.getBooksPage(offset, nextPageOffset);

        return (
            <div className="container mt-5">
                <div className="row">
                    <div className="col">
                        <div className="table-responsive">
                            <table className="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">Name</th>
                                        <th scope="col">Category</th>
                                        <th scope="col">Author</th>
                                        <th scope="col">Available Copies</th>
                                    </tr>
                                </thead>
                                <tbody>{books}</tbody>
                            </table>
                        </div>
                        <Link className="btn btn-dark mb-3" to="/books/create">
                            Add New Book
                        </Link>
                        <ReactPaginate
                            previousLabel="Previous"
                            nextLabel="Next"
                            breakLabel={<span className="break-me">...</span>}
                            pageCount={pageCount}
                            marginPagesDisplayed={2}
                            pageRangeDisplayed={3}
                            onPageChange={this.handlePageClick}
                            containerClassName="pagination justify-content-center"
                            activeClassName="active"
                            pageClassName="page-item"
                            pageLinkClassName="page-link"
                            previousClassName="page-item"
                            previousLinkClassName="page-link"
                            nextClassName="page-item"
                            nextLinkClassName="page-link"
                        />
                    </div>
                </div>
            </div>
        );
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        });
    };

    getBooksPage = (offset, nextPageOffset) => {
        return this.props.books
            .slice(offset, nextPageOffset)
            .map((term) => <BookTerm key={term.id} term={term} onDelete={this.props.onDelete} onEdit={this.props.onEdit} onMarkTaken={this.props.onMarkTaken} />);
    };
}

export default Books;
