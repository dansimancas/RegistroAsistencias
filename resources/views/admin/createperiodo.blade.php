<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title>Administrar Periodos</title>
        <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h2>Crear periodo</h2>
                    <form method="POST" action="/admin/periodo/store" class="form">
                        <div class="form-group">
                            <label for="periodo">Periodo</label>
                            <input type="text" class="form-control" id="periodo" name="periodo">
                        </div>
                        <div class="form-group">
                            <label for="active">Active</label>
                            <select class="form-control" id="active" name="active">
                                <option value="0">Inactivo</option>
                                <option value="1">Activo</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="/js/jquery.min.js"></script>
        <script src="/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
