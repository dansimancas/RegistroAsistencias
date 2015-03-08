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


/*
muestra cursos que dicta el profesor id. Devuelve colección de cursos (OK)

*/
Route::get('teacher/{id}/courses', 'CoursesController@showCoursesByTeacher');

/*
muestra la lista de estudiantes del curso NRC del profesor id ya capturado
devuelve coleccción de estudiantes (OK)
*/

Route::get('course/{NRC}/students', 'CoursesController@showStudentsByCourse');

/*
//muestra la lista de estudiantes
Route::get('/estudiantes', 'ListarCursos@show');

//muestra la informacion del estudiante id
Route::get('/estudiantes/{id}', 'ListarCursos@show');

//muestra los cursos en los que esta inscrito el estudiante id
Route::get('/estudiantes/{id}/cursos', 'ListarCursos@show');


Route::controllers([
	'auth' => 'Auth\AuthController',
	'password' => 'Auth\PasswordController',
]);
*/