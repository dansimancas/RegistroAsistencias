<?php namespace App\Http\Middleware;

use Closure;

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
		return $next($request);
	}

}
