<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/', 'WelcomeController@index');

Route::get('home', 'HomeController@index');

// Ruta para desplegar los cursos de un profesor por defecto
Route::get('cursos', 'ListasController@index');

// Ruta para buscar la lista de cursos del profesor cuyo código sea pasado por la variable {id}
// y desplegado con el controlador ListasController en el método show.
Route::get('{id}/cursos', 'ListasController@show');

Route::controllers([
	'auth' => 'Auth\AuthController',
	'password' => 'Auth\PasswordController',
]);
