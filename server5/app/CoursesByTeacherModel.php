<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class CoursesByTeacherModel extends Model {

<<<<<<< HEAD
    //Se asigna la vista de CoursesByTeacher existente en la base de datos. Esta vista contiene la siguiente información:
    //TEACHERID (código del PROFESOR), SUBJECTNAME (nombre de la materia), NRC (código de la materia), SECTION (sección),
    //NAMES, LASTNAMES (nombres y apellidos del profesor, respectivamente).

=======
    /**
     * Carga la vista que contiene la información de los cursos que dictan los profesores.
     * @var string
     */
>>>>>>> origin/master
    protected $table = 'CoursesByTeacher';

}
