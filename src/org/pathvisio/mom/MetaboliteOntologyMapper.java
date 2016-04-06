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

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.bridgedb.IDMapper;
import org.bridgedb.IDMapperException;
import org.pathvisio.core.debug.Logger;
import org.pathvisio.desktop.PvDesktop;
import org.pathvisio.desktop.plugin.Plugin;

/**
 * 
 * Implements PathVisio plugin class
 * Adds to menu items
 * @author mku
 *
 */
public class MetaboliteOntologyMapper implements Plugin {
	
	private PvDesktop desktop;
	private IDMapper ontMapper;
	private JMenu momMenu;
	private MetaboliteOntologyMapper plugin;

	public void init(PvDesktop desktop) {
		plugin = this;
		this.desktop = desktop;
		momMenu = new JMenu("MetaboliteOntologyMapper");
		JMenuItem addMapper = new JMenuItem("Load mapper");
		JMenuItem removeMapper = new JMenuItem("Remove mapper");

		addMapper.addActionListener(new AddMapperAction(plugin, desktop));
		removeMapper.addActionListener(new RemoveMapperAction(plugin, desktop));

		momMenu.add(addMapper);
		momMenu.add(removeMapper);
		
		desktop.registerSubMenu("Plugins", momMenu);
	}

	public void done() {
		try {
			desktop.getSwingEngine().getGdbManager().removeMapper(ontMapper);
		} catch (IDMapperException e) {
			Logger.log.trace("Could not uninstall metabolite ontology mapper.");
		}
	}

	public IDMapper getOntMapper() {
		return ontMapper;
	}

	public void setOntMapper(IDMapper ontMapper) {
		this.ontMapper = ontMapper;
	}
 
}
