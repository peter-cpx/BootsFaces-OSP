/**
 *  Copyright 2014 - 17 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
 *  
 *  This file is part of BootsFaces.
 *  
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */

package net.bootsfaces.component.container;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:container /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.container.Container")
public class ContainerRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:container.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 * 
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:container.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		Container container = (Container) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = container.getClientId();

		boolean fluid = container.isFluid();
		String style = container.getStyle();
		String sc = container.getStyleClass();

		String c = (fluid ? "container-fluid" : "container");
		if (sc != null) {
			c += " " + sc;
		}

		rw.startElement("div", container);
		rw.writeAttribute("id", clientId, "id");
		String dir = container.getDir();
		if (null != dir)
			rw.writeAttribute("dir", dir, "dir");

		Tooltip.generateTooltip(context, container, rw);
		if (style != null) {
			rw.writeAttribute("style", style, "style");
		}
		rw.writeAttribute("class", c, "class");
		beginDisabledFieldset(container, rw);
	}

	/**
	 * This methods generates the HTML code of the current b:container.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 * 
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:container.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		Container container = (Container) component;
		ResponseWriter rw = context.getResponseWriter();
		beginDisabledFieldset(container, rw);
		rw.endElement("div");
		Tooltip.activateTooltips(context, container);
	}
}
