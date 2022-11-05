#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 uvCoord;

out vec2 passUvCoord;

uniform mat4 modelMatrix;

void main() {
    passUvCoord = uvCoord;
    gl_Position = modelMatrix * vec4(position, 1);
}