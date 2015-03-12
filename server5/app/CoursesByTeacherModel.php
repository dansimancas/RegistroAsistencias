<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class CoursesByTeacherModel extends Model {

    /**
     * Carga la vista que contiene la información de los cursos que dictan los profesores.
     * @var string
     */
    protected $table = 'CoursesByTeacher';

}
