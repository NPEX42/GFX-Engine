package gfx.engine.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.co.nerdprogramming.gfx.engine.api.GFX;
import uk.co.nerdprogramming.gfx.engine.shaders.GLSLShader;

class ShaderTests {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		GFX.INIT_TEST_ONLY();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		GFX.Destruct();
	}

	@Test
	void Test_ShaderLoad() {
		assertNull(GLSLShader.LoadShader("NULL"));
		assertNotNull(GLSLShader.LoadShaderJAR("uk/co/nerdprogramming/gfx/res/2D.glsl"));
	}

}
