//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package RFIDListener;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

final class TrayIconHandler {
    private static TrayIcon trayIcon;

    TrayIconHandler() {
    }

    private static PopupMenu getPopupMenu() {
        PopupMenu popupMenu = trayIcon.getPopupMenu();
        if (popupMenu == null) {
            popupMenu = new PopupMenu();
        }

        return popupMenu;
    }

    private static void add(MenuItem item) {
        if (!isNotRegistered()) {
            PopupMenu popupMenu = getPopupMenu();
            popupMenu.add(item);
            trayIcon.setPopupMenu(popupMenu);
        }
    }

    private static void addToMenu(String menu, MenuItem item) {
        if (!isNotRegistered()) {
            if (isNotExistsMenu(menu)) {
                addMenu(menu);
            }

            int i = 0;

            for(int j = getPopupMenu().getItemCount(); i < j; ++i) {
                if (getPopupMenu().getItem(i) instanceof Menu) {
                    Menu menuitem = (Menu)getPopupMenu().getItem(i);
                    if (menuitem.getLabel().equals(menu)) {
                        menuitem.add(item);
                        getPopupMenu().insert(menuitem, i);
                        break;
                    }
                }
            }

        }
    }

    public static boolean isRegistered() {
        return trayIcon != null && getPopupMenu() != null;
    }

    public static boolean isNotRegistered() {
        return !isRegistered();
    }

    public static boolean isExistsMenu(String menu) {
        if (isNotRegistered()) {
            return false;
        } else {
            int i = 0;

            for(int j = getPopupMenu().getItemCount(); i < j; ++i) {
                if (getPopupMenu().getItem(i) instanceof Menu) {
                    Menu item = (Menu)getPopupMenu().getItem(i);
                    if (item.getLabel().equals(menu)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public static boolean isNotExistsMenu(String menu) {
        return !isExistsMenu(menu);
    }

    public static void registerTrayIcon(Image image) {
        registerTrayIcon(image, (String)null, (ActionListener)null);
    }

    public static void registerTrayIcon(Image image, String toolTip) {
        registerTrayIcon(image, toolTip, (ActionListener)null);
    }

    public static void registerTrayIcon(Image image, String toolTip, ActionListener action) {
        if (SystemTray.isSupported()) {
            if (trayIcon != null) {
                trayIcon = null;
            }

            trayIcon = new TrayIcon(image);
            trayIcon.setImageAutoSize(true);
            if (toolTip != null) {
                trayIcon.setToolTip(toolTip);
            }

            if (action != null) {
                trayIcon.addActionListener(action);
            }

            try {
                TrayIcon[] var3 = SystemTray.getSystemTray().getTrayIcons();
                int var4 = var3.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    TrayIcon registeredTrayIcon = var3[var5];
                    SystemTray.getSystemTray().remove(registeredTrayIcon);
                }

                SystemTray.getSystemTray().add(trayIcon);
            } catch (AWTException var7) {
                ;
            }
        }

    }

    public static void setToolTip(String toolTip) {
        if (!isNotRegistered()) {
            trayIcon.setToolTip(toolTip);
        }
    }

    public static void setImage(Image image) {
        if (!isNotRegistered()) {
            trayIcon.setImage(image);
        }
    }

    public static void displayMessage(String caption, String text, MessageType messageType) {
        if (!isNotRegistered()) {
            trayIcon.displayMessage(caption, text, messageType);
        }
    }

    public static void addSeparator() {
        if (!isNotRegistered()) {
            getPopupMenu().addSeparator();
        }
    }

    public static void addSeparator(String menu) {
        if (!isNotRegistered()) {
            int i = 0;

            for(int j = getPopupMenu().getItemCount(); i < j; ++i) {
                if (getPopupMenu().getItem(i) instanceof Menu) {
                    Menu item = (Menu)getPopupMenu().getItem(i);
                    if (item.getLabel().equals(menu)) {
                        item.addSeparator();
                        getPopupMenu().insert(item, i);
                        break;
                    }
                }
            }

        }
    }

    public static void addMenu(String menu) {
        add(new Menu(menu));
    }

    public static void addItem(String label, ActionListener action) {
        MenuItem menuItem = new MenuItem(label);
        menuItem.addActionListener(action);
        add(menuItem);
    }

    public static void addCheckBox(String label, ItemListener action) {
        addCheckBox(label, false, action);
    }

    public static void addCheckBox(String label, boolean state, ItemListener action) {
        CheckboxMenuItem checkboxMenuItem = new CheckboxMenuItem(label, state);
        checkboxMenuItem.addItemListener(action);
        add(checkboxMenuItem);
    }

    public static void addItemToMenu(String menu, String label, ActionListener action) {
        MenuItem menuItem = new MenuItem(label);
        menuItem.addActionListener(action);
        addToMenu(menu, menuItem);
    }

    static void toggleCheckbox(String menu) {
        int i = 0;

        for(int j = getPopupMenu().getItemCount(); i < j; ++i) {
            if (getPopupMenu().getItem(i) instanceof CheckboxMenuItem) {
                CheckboxMenuItem menuitem = (CheckboxMenuItem)getPopupMenu().getItem(i);
                if (menuitem.getLabel().equals(menu) && menuitem.getState()) {
                    menuitem.setState(false);
                } else {
                    menuitem.setState(true);
                }
            }
        }

    }

    public static void addCheckBoxToMenu(String menu, String label, ItemListener action) {
        addCheckBoxToMenu(menu, label, false, action);
    }

    public static void addCheckBoxToMenu(String menu, String label, boolean state, ItemListener action) {
        CheckboxMenuItem checkboxMenuItem = new CheckboxMenuItem(label, state);
        checkboxMenuItem.addItemListener(action);
        if (checkboxMenuItem.getLabel().equals("No Port available")) {
            checkboxMenuItem.setEnabled(false);
        }

        addToMenu(menu, checkboxMenuItem);
    }

    static void refreshCheckboxMenu(String menu, String subMenu) {
        int i = 0;

        for(int j = getPopupMenu().getItemCount(); i < j; ++i) {
            if (getPopupMenu().getItem(i) instanceof Menu) {
                Menu menuitem = (Menu)getPopupMenu().getItem(i);
                if (menuitem.getLabel().equals(menu)) {
                    for(int k = 0; k < menuitem.getItemCount(); ++k) {
                        if (menuitem.getItem(k) instanceof CheckboxMenuItem && menuitem.getItem(k).getLabel().equals(subMenu)) {
                            ((CheckboxMenuItem)menuitem.getItem(k)).setState(true);
                        } else {
                            ((CheckboxMenuItem)menuitem.getItem(k)).setState(false);
                        }
                    }
                }
            }
        }

    }
}
