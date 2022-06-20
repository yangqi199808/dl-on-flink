/*
 * Copyright 2022 Deep Learning on Flink Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.flinkextended.flink.ml.cluster.storage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** A local file storage implementation of Storage that can support running in Flink MiniCluster. */
public class MemoryStorageImpl implements Storage {

    private Map<String, byte[]> mTable = new HashMap<>();

    public MemoryStorageImpl() {}

    @Override
    public byte[] getValue(String path) {
        return mTable.get(path);
    }

    @Override
    public void setValue(String path, byte[] value) throws IOException {
        mTable.put(path, value);
    }

    @Override
    public void removeValue(String path) throws IOException {
        mTable.remove(path);
    }

    @Override
    public List<String> listChildren(String path) throws IOException {
        // path of memory storage do not have child
        return null;
    }

    @Override
    public boolean exists(String path) throws IOException {
        return mTable.containsKey(path);
    }

    @Override
    public void clear() {
        mTable.clear();
    }
}