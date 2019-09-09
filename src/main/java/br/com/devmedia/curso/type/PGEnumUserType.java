package br.com.devmedia.curso.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

/**
 * PGEnumUserType
 */
public class PGEnumUserType extends EnumType {

	private static final long serialVersionUID = -7987483586905330465L;

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {

		if ( value == null )
			st.setObject(index, null);
		else
			st.setObject(index, value.toString(), 1111);
	}
}