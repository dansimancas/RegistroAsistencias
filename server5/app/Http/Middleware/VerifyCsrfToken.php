<?php namespace App\Http\Middleware;

use Closure;
use Illuminate\Foundation\Http\Middleware\VerifyCsrfToken as BaseVerifier;

class VerifyCsrfToken extends BaseVerifier
{

    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  \Closure $next
     * @return mixed
     */
    //@todo:asegurar aplicacion

    public function handle($request, Closure $next)
    {
        return $this->addCookieToResponse($request, $next($request));//parent::handle($request, $next);
    }

}
