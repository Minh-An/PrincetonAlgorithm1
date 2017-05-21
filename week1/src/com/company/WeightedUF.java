package com.company;

class WeightedUF
{
    int[] _id;
    int[] _size;
    int _count;

    public WeightedUF(int n)
    {
        for (int i = 0; i < n; ++i)
        {
            _id[i] = i;
            _size[i] = 1;
        }
        _count = n;
    }

    private int root(int i)
    {
        while (i != _id[i])
        {
            //_id[i] = _id[_id[i]];
            i = _id[i];
        }
        return i;
    }

    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    public void union(int p, int q)
    {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ) return;
        if(_size[rootP] > _size[rootQ])
        {
            _id[rootQ] = rootP;
            _size[rootP] += _size[rootQ];
        }
        else
        {
            _id[rootP] = rootQ;
            _size[rootQ] += _size[rootP];
        }
        _count--;

    }
}


