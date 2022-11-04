#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 uvCoord;

uniform mat4 worldMatrix;

void main() {
    gl_Position =  worldMatrix * vec4(position, 1);
}