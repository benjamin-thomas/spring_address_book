(function () {

    function _delete(url, removeSelector) {
        fetch(url, {method: 'DELETE'})
            .then(res => {
                if (!res.ok) {
                    throw "Something bad happened! (" + res.status + ")";
                }
                document.querySelectorAll(removeSelector).forEach(function (elem) {
                    elem.remove();
                })
            })
            .catch(err => {
                alert(err)
            });
    }

    $(document).ready(function () {

        $('.on-click-delete').click(function (evt) {
            evt.preventDefault();
            _delete(evt.currentTarget.href, evt.currentTarget.dataset['onDeleteRemove']);
        });

    });
})();
