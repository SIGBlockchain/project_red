package sigblockchain.projectred.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    @Test
    void testConstructor(){
        Block block = new Block(10, 20, 30, "hello", "blockChain", 10, "Data");
        assertNotNull(block);
    }

    @Test
    void versionGetter()    {
        Block block = new Block(10, 20, 30, "hello", "blockChain", 10, "Data");
        int v = block.getVersion();
        assertEquals(v, 10);
    }

    @Test
    void heightGetter()    {
        Block block = new Block(10, 20, 30, "hello", "blockChain", 10, "Data");
        int v = block.getHeight();
        assertEquals(v, 20);
    }

    @Test
    void timeStampGetter()    {
        Block block = new Block(10, 20, 30, "hello", "blockChain", 10, "Data");
        int v = block.getTimestamp();
        assertEquals(v, 30);
    }

    @Test
    void previousHashGetter()    {
        Block block = new Block(10, 20, 30, "hello", "blockChain", 10, "Data");
        String v = block.getPreviousHash();
        assertEquals(v, "hello");
    }

    @Test
    void merkleRootHashGetter()    {
        Block block = new Block(10, 20, 30, "hello", "blockChain", 10, "Data");
        String v = block.getMerkleRootHash();
        assertEquals(v, "blockChain");
    }

    @Test
    void dataLenGetter()    {
        Block block = new Block(10, 20, 30, "hello", "blockChain", 10, "Data");
        int v = block.getDataLen();
        assertEquals(v, 10);
    }

    @Test
    void dataGetter()    {
        Block block = new Block(10, 20, 30, "hello", "blockChain", 10, "Data");
        String v = block.getData();
        assertEquals(v, "Data");
    }
}
