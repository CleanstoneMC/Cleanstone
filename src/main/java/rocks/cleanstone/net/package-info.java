/**
 * Architecture related to establishing and controlling server and player client IO over the network
 *
 * <p>This includes session/login control, packet encoding/decoding, the client protocol and
 * version-related conversions.</p>
 * <p>
 * Information leaving this stage is transmitted to other packages using events such as {@link
 * rocks.cleanstone.net.event.InboundPacketEvent} or {@link rocks.cleanstone.endpoint.minecraft.vanilla.net.login.event.AsyncLoginEvent}
 */
package rocks.cleanstone.net;