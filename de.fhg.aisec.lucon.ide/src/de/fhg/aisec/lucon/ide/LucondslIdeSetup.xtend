/*
 * generated by Xtext 2.12.0
 */
package de.fhg.aisec.lucon.ide

import com.google.inject.Guice
import de.fhg.aisec.lucon.LucondslRuntimeModule
import de.fhg.aisec.lucon.LucondslStandaloneSetup
import org.eclipse.xtext.util.Modules2

/**
 * Initialization support for running Xtext languages as language servers.
 */
class LucondslIdeSetup extends LucondslStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new LucondslRuntimeModule, new LucondslIdeModule))
	}
	
}