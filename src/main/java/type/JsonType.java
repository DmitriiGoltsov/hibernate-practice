package type;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonType implements UserType {

    @Override
    public int getSqlType() {
        return 0;
    }

    @Override
    public Class returnedClass() {
        return null;
    }

    @Override
    public boolean equals(Object object, Object j1) {
        return false;
    }

    @Override
    public int hashCode(Object object) {
        return 0;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, int i, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object object, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {

    }

    @Override
    public Object deepCopy(Object object) {
        return null;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object object) {
        return null;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) {
        return null;
    }
}
