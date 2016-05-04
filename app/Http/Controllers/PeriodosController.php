<?php

namespace App\Http\Controllers;

use App\PeriodosModel;
use App\Http\Requests;
use Illuminate\Http\Request;

class PeriodosController extends Controller {

    public function index() {
        $periodos = PeriodosModel::all();
        return view('admin.periodos')->with(['periodos' => $periodos]);
    }

    public function create() {
        return view('admin.createperiodo');
    }

    public function store(Request $request) {
        $rules = ['periodo' => 'required|min:6|max:6', 'active' => 'required'];
        $this->validate($request, $rules);

        PeriodosModel::create($request->only('periodo', 'active'));
        
        return redirect('admin/periodos');
    }

    public function edit($id) {
        $periodo = PeriodosModel::find($id);
        return view('admin.editperiodo')->with(['periodo' => $periodo]);
    }

    public function update(Request $request, $id) {
        $rules = ['periodo' => 'required|min:6|max:6', 'active' => 'required'];
        $this->validate($request, $rules);

        $periodo = PeriodosModel::find($id);
        $periodo->periodo = $request['periodo'];
        $periodo->active = $request['active'];
        $periodo->save();

        return redirect('admin/periodos');
    }

}
