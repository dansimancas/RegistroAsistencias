<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class StudentsByCourseModel extends Model {

    /**
     * @var string Carga la vista que contiene la información de los estudiantes que asisten a cursos.
     */
    protected $table = 'StudentsByCourse';

}
