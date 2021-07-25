package com.eponymouse.zktxviewer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.graphics.glutils.KTXTextureData;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

import java.io.File;

public class ViewerApp extends ApplicationAdapter
{
    private final File file;
    private Texture texture;
    private ShaderProgram shader;
    private ImmediateModeRenderer renderer;

    public ViewerApp(File file)
    {
        this.file = file;
    }

    @Override
    public void create()
    {
        texture = new Texture(Gdx.files.absolute(file.getAbsolutePath()), true);
        shader = new ShaderProgram(Gdx.files.internal("vertex.glsl"), Gdx.files.internal("fragment.glsl"));
        renderer = new ImmediateModeRenderer20(10, false, false, 1, shader);
    }

    @Override
    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        texture.bind(0);
        renderer.begin(new Matrix4(), GL20.GL_TRIANGLE_FAN);
        renderer.texCoord(0, 0);
        renderer.vertex(-1, -1, 0);
        renderer.texCoord(1, 0);
        renderer.vertex(1, -1, 0);
        renderer.texCoord(1, 1);
        renderer.vertex(1, 1, 0);
        renderer.texCoord(0, 1);
        renderer.vertex(-1, 1, 0);
        renderer.end();
    }
}
