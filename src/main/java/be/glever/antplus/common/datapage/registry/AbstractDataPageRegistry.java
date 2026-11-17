package be.glever.antplus.common.datapage.registry;

import static java.lang.String.format;

import be.glever.antplus.common.datapage.AbstractAntPlusDataPage;
import be.glever.util.logging.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * A DataPageRegistry holds Ant+ {@link AbstractAntPlusDataPage}s for a specific device.
 * As some datapages are specific for a given device and others are common ( defined in {@link CommonDataPageRegistry} ),
 * this base class allows grouping multiple registries together for a given Ant+ device type.
 */
public abstract class AbstractDataPageRegistry {

    private final Map<Byte, DataPageBuilder> registry = new HashMap<>();

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
        for (Map.Entry<Byte, DataPageBuilder> entry : src.registry.entrySet()) {
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
        DataPageBuilder dpBuilder = registry.get(payLoadBytes[0]);
        if (dpBuilder == null) {
            LOG.info(() -> format("Data page number  0x%02X not yet supported",payLoadBytes[0]));
            return null;
        }
        AbstractAntPlusDataPage dataPage = dpBuilder.construct(payLoadBytes);
        LOG.debug(() -> format("Data page %s number 0x%02X ",dataPage.getClass().getSimpleName(),dataPage.getPageNumber()));
        return dataPage;
    }

    /**
     * Add.
     *
     * @param pageNumber the page number
     * @param builder the builder
     */
    protected void add(byte pageNumber, DataPageBuilder builder) {
        if (this.registry.containsKey(pageNumber)) {
            throw new IllegalStateException("Duplicate DataPage defined in registry: " + pageNumber);
        }
        this.registry.put(pageNumber, builder);
    }

    /**
     * Interface for Data Page Builder.
     */
    public interface DataPageBuilder {
        /**
         * Construct.
         *
         * @param bytes the bytes
         * @return the construct.
         */
        AbstractAntPlusDataPage construct(byte[] bytes);
    }
}
