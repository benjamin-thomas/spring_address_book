<#import "../layouts/main.ftlh" as layout>
<#import "/spring.ftl" as spring>
<#--<@spring.bind "country" />-->

<@layout.main title="New country">
    <h1>New country</h1>

    <form action="/countries" method="POST">
        Name: <@spring.formInput "country.name" />
        <@spring.showErrors "<br>", 'error' />
        <br>
        Code: <@spring.formInput "country.code" />
        <@spring.showErrors "<br>", 'error' />

        <br>
        <input type="submit" value="Create country">

    </form>

</@layout.main>