package be.glever.antplus.common.datapage.registry;

import static java.lang.String.format;

import be.glever.antplus.common.datapage.AbstractAntPlusDataPage;
import be.glever.util.logging.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A DataPageRegistry holds Ant+ {@link AbstractAntPlusDataPage}s for a specific
 * device.
 * As some datapages are specific for a given device and others are common (
 * defined in {@link CommonDataPageRegistry} ),
 * this base class allows grouping multiple registries together for a given Ant+
 * device type.
 */
public abstract class AbstractDataPageRegistry {

    private final Map<Byte, Function<byte[], AbstractAntPlusDataPage>> registry = new HashMap<>();

    private static final Log LOG = Log.getLogger(AbstractDataPageRegistry.class);

    /**
     * Constructs a new {@link AbstractDataPageRegistry}.
     */
    public AbstractDataPageRegistry() {
    }

    /**
     * Constructs a new {@link AbstractDataPageRegistry}.
     *
     * @param sourceRegistries the source registries
     */
    public AbstractDataPageRegistry(AbstractDataPageRegistry... sourceRegistries) {
        for (AbstractDataPageRegistry src : sourceRegistries) {
            addAllFromRegistry(src);
        }
    }

    private void addAllFromRegistry(AbstractDataPageRegistry src) {
        for (Map.Entry<Byte, Function<byte[], AbstractAntPlusDataPage>> entry : src.registry.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Construct data page.
     *
     * @param payLoadBytes the pay load bytes
     * @return the construct data page.
     */
    public AbstractAntPlusDataPage constructDataPage(byte[] payLoadBytes) {
        byte pageNumber = payLoadBytes[0];
        AbstractAntPlusDataPage dataPage = registry.get(pageNumber).apply(payLoadBytes);
        if (dataPage == null) {
            LOG.info(() -> format("Data page number  0x%02X not yet supported", pageNumber));
            return null;
        }
        LOG.debug(() -> format("Data page %s number 0x%02X ", dataPage.getClass().getSimpleName(),
                dataPage.getPageNumber()));
        return dataPage;
    }

    /**
     * Adds a new data page costructor to the registry labeled with the given page
     * number.
     *
     * @param pageNumber  the page number
     * @param constructor the data page constructor function
     */
    protected void add(byte pageNumber, Function<byte[], AbstractAntPlusDataPage> constructor) {
        if (this.registry.containsKey(pageNumber)) {
            throw new IllegalStateException("Duplicate DataPage defined in registry: " + pageNumber);
        }
        this.registry.put(pageNumber, constructor);
    }
}
