#version 330

in vec2 passUvCoord;

out vec4 fragColor;

uniform sampler2D sampler;
uniform int top;

void main() {

    vec2 uvCoord = passUvCoord;

    if (top == 1)
        uvCoord.y = 1.0 - passUvCoord.y;

    fragColor = texture(sampler, uvCoord);

    if (fragColor.w < 1.0)
    	discard;
}