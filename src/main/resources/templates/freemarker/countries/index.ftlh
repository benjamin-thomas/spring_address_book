<#import "../layouts/main.ftlh" as layout>

<@layout.main title="Listing countries">

    <h1>Hello Countries</h1>

    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Code</th>
            <th></th>
        </tr>
        </thead>

        <tbody>

        <#list countries as country>
            <#assign
            resourceUrl = "${INDEX_URL}/${country.id}"
            rowID = "row-${country.id}"
            >

            <tr id="${rowID}">
                <td>${country.name}</td>
                <td>${country.code}</td>
                <td>
                    <a href="${resourceUrl}">Show</a>
                    <a href="${resourceUrl}/edit">Edit</a>
                    <a href="${resourceUrl}/edit2">Edit2</a>

                    <a
                            href="${resourceUrl}"
                            class="on-click-delete"
                            data-on-delete-remove="#${rowID}"
                    >Delete</a>

                </td>
            </tr>
        </#list>

        </tbody>
    </table>

    <p>
        <a href="${NEW_URL}">Create a new country</a>
    </p>

</@layout.main>