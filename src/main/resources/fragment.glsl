varying vec2 v_tex0;
uniform sampler2D u_sampler0;

void main()
{
    gl_FragColor = texture2D(u_sampler0, v_tex0);
}
