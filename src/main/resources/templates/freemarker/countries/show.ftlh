<#import "../layouts/main.ftlh" as layout>

<@layout.main title="Show country">

    <h1>Hello Country</h1>

    <table>

        <tbody>

        <tr>
            <th>Name</th>
            <td>${country.name}</td>
        </tr>

        <tr>
            <th>Code</th>
            <td>${country.getCode()}</td>
        </tr>

        </tbody>
    </table>


</@layout.main>