import React from "react";
import {Link} from "react-router-dom";

const BookTerm = (props) => {
    return(
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.category}</td>
            <td>{props.term.author.name} {props.term.author.surname}</td>
            <td>{props.term.availableCopies}</td>
            <td className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger ml-2"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
            </td>
            <td className={"text-right"}>
                <Link className={"btn btn-info ml-2"}
                        onClick={() => props.onEdit(props.term.id)}
                        to={`/books/edit/${props.term.id}`}>
                        Edit
                    </Link>
            </td>
            <td className={"text-right"}>
                <a title={"MarkTaken"} className={"btn btn-secondary ml-2"}
                   onClick={() => props.onMarkTaken(props.term.id)}>
                    Lend Book
                </a>
            </td>
        </tr>
    )
}

export default BookTerm;