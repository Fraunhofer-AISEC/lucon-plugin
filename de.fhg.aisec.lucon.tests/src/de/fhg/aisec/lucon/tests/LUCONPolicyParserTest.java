package de.fhg.aisec.lucon.tests;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.GeneratorDelegate;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Injector;

import de.fhg.aisec.lucon.LucondslStandaloneSetup;

/**
 * Test case for DSL-to-Prolog generation of LUCON policies.
 * 
 * @author Julian Schuette (julian.schuette@aisec.fraunhofer.de)
 *
 */
public class LUCONPolicyParserTest {

	private static Injector injector;
	private static XtextResourceSet resourceSet;

	@BeforeClass
	public static void onlyOnce() {
		// do this only once per application
		injector = new LucondslStandaloneSetup().createInjectorAndDoEMFRegistration();
		 
		// obtain a resourceset from the injector
		resourceSet = injector.getInstance(XtextResourceSet.class);
	}
	
	@Test
	public void testPrologFileGeneration() {
		// load a resource by URI, in this case from the file system
		Resource resource = resourceSet.getResource(URI.createFileURI("res/testpolicy.lucon"), true);
		
		// Validation
		IResourceValidator validator = ((XtextResource)resource).getResourceServiceProvider().getResourceValidator();
		List<Issue> issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		for (Issue issue : issues) {
		  System.out.println(issue.getMessage());
		}		
		assertTrue("Expected 0 validation issues", issues.size() == 0);
		
		// Code Generator
		GeneratorDelegate generator = injector.getInstance(GeneratorDelegate.class);
		InMemoryFileSystemAccess fsa = new InMemoryFileSystemAccess();
		generator.doGenerate(resource, fsa);

		assertSame("Expecting 1 generated Prolog file", 1,fsa.getTextFiles().size());
	}
}
