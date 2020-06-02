<#import "../layouts/main.ftlh" as layout>
<#import "/spring.ftl" as spring>
<#--<@spring.bind "countryFormEdit" />-->

<@layout.main title="New country">
    <h1>New country</h1>

    <form method="POST">
        Name: <@spring.formInput "countryFormEdit.name" />
        <@spring.showErrors "<br>", 'error' />
        <br>

        <input type="submit" value="Update country">

    </form>

</@layout.main>