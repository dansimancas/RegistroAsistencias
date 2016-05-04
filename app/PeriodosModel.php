<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class PeriodosModel extends Model {

    protected $primaryKey = 'id';
    protected $table = 'periodosactivos';
    protected $fillable = ['periodo', 'active'];

    public function periodosActivos() {
        $periodos = PeriodosModel::where('active', '=', 1)->get();
        return $periodos;
    }

}
