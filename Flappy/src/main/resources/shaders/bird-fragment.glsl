#version 330

in vec2 passUvCoord;

out vec4 fragColor;

uniform sampler2D sampler;

void main() {
    fragColor = texture(sampler, passUvCoord);

    if (fragColor.w < 1.0)
    	discard;
}