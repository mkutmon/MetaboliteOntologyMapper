// MetaboliteOntologyMapper,
// Plugin for PathVisio
// Copyright 2016 BiGCaT Bioinformatics
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
package org.pathvisio.mom;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.bridgedb.IDMapperException;
import org.pathvisio.desktop.PvDesktop;

/**
 * 
 * Uninstalls Metabolite Ontology Mapper
 * @author mku
 *
 */
public class RemoveMapperAction extends AbstractAction {

	private MetaboliteOntologyMapper plugin;
	private PvDesktop desktop;

	public RemoveMapperAction(MetaboliteOntologyMapper plugin, PvDesktop desktop) {
		this.plugin = plugin;
		this.desktop = desktop;
		putValue(NAME, "Load mapper");
	}

	public void actionPerformed(ActionEvent e)  {
		if(plugin.getOntMapper() != null) {
			try {
				desktop.getSwingEngine().getGdbManager().removeMapper(plugin.getOntMapper());
				JOptionPane.showMessageDialog(desktop.getFrame(), "Metabolite Ontology Mapper has been uninstalled.");
			} catch (IDMapperException e1) {
				JOptionPane.showMessageDialog(desktop.getFrame(), "Metabolite Ontology Mapper could not be uninstalled.\n" + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
