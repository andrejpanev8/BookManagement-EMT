import React, { Component } from "react";
import { Link } from "react-router-dom";

const CategoriesList = (props) => {
    return(
        <div className={"container"}>
            <div className="table-responsive">
            <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Category Name</th>
                    </tr>
                </thead>
                <tbody>
                    {props.categories.map(category => (
                        <tr key={category.id}>
                            <td>{category}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            </div>
        </div>
    )
}
export default CategoriesList