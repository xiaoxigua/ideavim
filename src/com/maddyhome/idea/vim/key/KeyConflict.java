package com.maddyhome.idea.vim.key;

/*
 * IdeaVim - A Vim emulator plugin for IntelliJ Idea
 * Copyright (C) 2003-2005 Rick Maddy
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeyConflict {
  public KeyConflict(KeyStroke keyStroke) {
    this.keyStroke = keyStroke;
    pluginWins = true;
  }

  public KeyStroke getKeyStroke() {
    return keyStroke;
  }

  public boolean hasConflict() {
    return ideaActions.size() > 0 && pluginActions.size() > 0;
  }

  public boolean isPluginWins() {
    return pluginWins;
  }

  public void setPluginWins(boolean pluginWins) {
    this.pluginWins = pluginWins;
  }

  public HashMap<String, Integer> getIdeaActions() {
    return ideaActions;
  }

  public void addIdeaAction(String action) {
    putIdeaAction(action, -1);
  }

  public void resetIdeaAction(String action) {
    putIdeaAction(action, -1);
  }

  public void putIdeaAction(String action, int pos) {
    ideaActions.put(action, pos);
  }

  public int removeIdeaAction(String action) {
    return ideaActions.remove(action);
  }

  public List<String> getPluginActions() {
    return pluginActions;
  }

  public void addPluginAction(String action) {
    pluginActions.add(action);
  }

  public boolean removePluginAction(String action) {
    return pluginActions.remove(action);
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final KeyConflict that = (KeyConflict)o;

    if (pluginWins != that.pluginWins) {
      return false;
    }
    if (!ideaActions.equals(that.ideaActions)) {
      return false;
    }
    if (!keyStroke.equals(that.keyStroke)) {
      return false;
    }

    return pluginActions.equals(that.pluginActions);
  }

  public int hashCode() {
    int result;
    result = keyStroke.hashCode();
    result = 29 * result + (pluginWins ? 1 : 0);
    result = 29 * result + ideaActions.hashCode();
    result = 29 * result + pluginActions.hashCode();
    return result;
  }

  public String toString() {
    final StringBuffer sb = new StringBuffer();
    sb.append("KeyConflict");
    sb.append("{keyStroke=").append(keyStroke);
    sb.append(", pluginWins=").append(pluginWins);
    sb.append(", ideaActions=").append(ideaActions);
    sb.append(", pluginActions=").append(pluginActions);
    sb.append('}');
    return sb.toString();
  }

  private KeyStroke keyStroke;
  private boolean pluginWins;
  private HashMap<String, Integer> ideaActions = new HashMap<String, Integer>();
  private List<String> pluginActions = new ArrayList<String>();
}