  #version 330

in vec2 passUvCoord;

out vec4 fragColor;

uniform vec2 resolution;

void main() {
    vec2 uv = gl_FragCoord.xy / resolution.xy;
    uv *= 1.0 - uv.yx;

    float vignette = uv.x * uv.y * 5.0;
    vignette = pow(vignette, 0.25);
    fragColor = vec4(0,0,0,1 - vignette);
}