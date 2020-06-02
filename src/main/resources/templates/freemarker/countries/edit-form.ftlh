<#import "../layouts/main.ftlh" as layout>
<#import "/spring.ftl" as spring>
<#--<@spring.bind "country" />-->

<@layout.main title="New country">
    <h1>New country</h1>

    <form action="/countries/${country.id}/edit" method="POST">
        Name: <@spring.formInput "country.name" />
        <@spring.showErrors "<br>", 'error' />
        <br>

        <input type="submit" value="Update country">

    </form>

</@layout.main>