<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class MatriculasModel extends Model {

    protected $table = 'MATRICULA_EXTERNA_SAVIO';
    protected $connection = 'sirius_bd';

    public function course(){
        return $this->belongsTo('App\CoursesModel', 'IDNUMBER', 'NRC_PERIODO_KEY');
    }

    public function student(){
        return  $this->belongsTo('App\StudentsModel', 'USERNAME', 'ID');
    }

    public function teacher(){
        return  $this->belongsTo('App\TeachersModel', 'USERNAME', 'ID');
    }

}
