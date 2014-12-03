<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="alert ${tipo} fade in" role="alert">

    <h4 align="center">${titulo}</h4>
    <hr>
    <p>${texto}</p>
    <p align="center">
        <button type="button" class="btn btn-default" id="btnOk">Ok</button>
        <script type="text/javascript">
            $("#btnOk").click(function() {
                $(".alert").alert('close');
            });
        </script>
    </p>	
</div>
