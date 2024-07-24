package de.fhg.aisec.lucon;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.OutputConfiguration;

/**
 * This OutputConfigurationProvider makes compiled files appear in folder
 * "compiled-policies" instead of "src-gen".
 * 
 * @author Julian Schütte (julian.schuette@aisec.fraunhofer.de)
 */
public class LuconOutputGenerator implements IOutputConfigurationProvider {

	/**
	 * @return a set of {@link OutputConfiguration} available for the generator
	 */
	public Set<OutputConfiguration> getOutputConfigurations() {
		OutputConfiguration defaultOutput = new OutputConfiguration(IFileSystemAccess.DEFAULT_OUTPUT);
		defaultOutput.setDescription("Output Folder");
		defaultOutput.setOutputDirectory("./compiled-policies");
		defaultOutput.setOverrideExistingResources(true);
		defaultOutput.setCreateOutputDirectory(true);
		defaultOutput.setCleanUpDerivedResources(true);
		defaultOutput.setSetDerivedProperty(true);

		HashSet<OutputConfiguration> out = new HashSet<OutputConfiguration>();
		out.add(defaultOutput);
		return out;
	}
}