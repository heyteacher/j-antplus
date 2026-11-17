package be.glever.antplus.fec.datapage;

import be.glever.antplus.common.datapage.registry.AbstractDataPageRegistry;
import be.glever.antplus.common.datapage.registry.CommonDataPageRegistry;
import be.glever.antplus.fec.datapage.main.FecDataPage16GeneralFeData;
import be.glever.antplus.fec.datapage.main.FecDataPage25Bike;

/**
 * Registry for Fec Data Page.
 */
public class FecDataPageRegistry extends AbstractDataPageRegistry {
    /**
     * Constructs a new {@link FecDataPageRegistry}.
     */
    public FecDataPageRegistry() {
        super(new CommonDataPageRegistry());

        add(FecDataPage16GeneralFeData.PAGE_NR, FecDataPage16GeneralFeData::new);
        add(FecDataPage25Bike.PAGE_NR, FecDataPage25Bike::new);
    }
}
