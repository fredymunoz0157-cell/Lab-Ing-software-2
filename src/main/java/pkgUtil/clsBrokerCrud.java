package pkgUtil;

import java.util.List;

/**
 * Utilidades genéricas para buscar, asociar y desasociar elementos en colecciones.
 * No depende de ninguna clase del proyecto (ni dominio ni Swing).
 *
 * @author Acer3
 */
public final class clsBrokerCrud {

    private clsBrokerCrud() {
    }

    public interface IIdentificable<T> {

        T opGetOUID();
    }

    public interface IIdentificableName<T> {

        T opGetName();
    }

    public static <OUIDType extends Comparable<OUIDType>, ItemType extends IIdentificable<OUIDType>>
            ItemType opGetItemType(OUIDType prmOUID, List<ItemType> prmCollection) {
        if (prmOUID == null || prmCollection == null) {
            return null;
        }
        for (ItemType varObj : prmCollection) {
            if (varObj.opGetOUID() != null && varObj.opGetOUID().compareTo(prmOUID) == 0) {
                return varObj;
            }
        }
        return null;
    }

    public static <ItemType> boolean opAssociateItemTo(ItemType prmItem, List<ItemType> prmCollection) {
        prmCollection.add(prmItem);
        return true;
    }

    public static <ItemType> boolean opDisassociateItemTo(ItemType prmItem, List<ItemType> prmCollection) {
        return prmCollection.remove(prmItem);
    }

    public static <OUIDType extends Comparable<OUIDType>, ItemType extends IIdentificableName<OUIDType>> ItemType
            opGetItemForName(OUIDType prmName, List<ItemType> prmCollection) {
        if (prmName == null || prmCollection == null) {
            return null;
        }
        for (ItemType varObj : prmCollection) {
            if (varObj.opGetName() != null && varObj.opGetName().compareTo(prmName) == 0) {
                return varObj;
            }
        }
        return null;
    }
}
