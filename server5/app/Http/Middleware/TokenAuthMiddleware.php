<?php namespace App\Http\Middleware;

use Closure;
use Request;

class TokenAuthMiddleware {

	/**
	 * Handle an incoming request.
	 *
	 * @param  \Illuminate\Http\Request  $request
	 * @param  \Closure  $next
	 * @return mixed
	 */
	public function handle($request, Closure $next)
	{

        /*Validar si el token corresponde al usuario que pide la info*/
        $username = Request::input('username');
        $token = Request::input('token');

        //Comprobación...

		return $next($request);
	}

}
