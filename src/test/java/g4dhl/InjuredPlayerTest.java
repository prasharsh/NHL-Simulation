package g4dhl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Test;

public class InjuredPlayerTest {

	@Test
	public void setTeamIdTest() {
		IInjuredPlayer mockInjuredPlayer = mock(InjuredPlayer.class);
		when(mockInjuredPlayer.setTeamId(1)).thenCallRealMethod();
		Assert.assertTrue(mockInjuredPlayer.setTeamId(1));
	}

	@Test
	public void setPlayerIdTest() {
		IInjuredPlayer mockInjuredPlayer = mock(InjuredPlayer.class);
		when(mockInjuredPlayer.setPlayerId(10)).thenCallRealMethod();
		Assert.assertTrue(mockInjuredPlayer.setPlayerId(10));
	}

	@Test
	public void setRecoveryDateTest() {
		IInjuredPlayer mockInjuredPlayer = mock(InjuredPlayer.class);
		String date = "2020-12-05";
		when(mockInjuredPlayer.setRecoveryDate(Date.valueOf(date))).thenCallRealMethod();
		Assert.assertTrue(mockInjuredPlayer.setRecoveryDate(Date.valueOf(date)));
	}

}
